import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author David Chang, Cole Risch
 * @version 2 This is a JUNIT test class for Thing42.java. Each method will test
 *          each thing42 method's functionality.
 */
public class Thing42Test {

	private Thing42<String, Integer> peer, peer1, peer2, peer3;
	Thing42<Object, Object> thing, thing1, thing2, thing3;
	private Object key1, key2;

	@Before
	public void init() {
		// create one set of Thing42s for general testing
		peer = new Thing42<String, Integer>("key1", 1, 25);
		peer1 = new Thing42<String, Integer>("peerKey1", 1, 25);
		peer2 = new Thing42<String, Integer>("peerKey2", 1, 50);
		peer3 = new Thing42<String, Integer>("peerKey3", 1, 75);

		// create a second set of Things 42s for testing near equality and other
		// strangeness
		thing = new Thing42<Object, Object>(key1, 50, null);
		thing1 = new Thing42<Object, Object>(key1, 50, null);
		thing2 = new Thing42<Object, Object>(key2, 50, null);
		thing3 = new Thing42<Object, Object>(key2, 75, null);
	}

	@Test
	public void testSetData() {
		// basic tests
		peer.setData(145555);
		assertTrue(145555 == peer.getData());

		// set null
		thing.setData(null);
		assertTrue(null == thing.getData());

		// set self-reference
		thing.setData(thing);
		assertTrue(thing == thing.getData());
	}

	@Test
	public void testAddPeer() {
		Thing42<String, Integer> testPeer = new Thing42<String, Integer>(
				"testKey", 5, 10);

		// basic test
		peer.addPeer(testPeer);
		assertEquals(testPeer, peer.getOnePeer("testKey"));

		// test adding null
		try {
			peer.addPeer(null);
		} catch (NullPointerException e) {
			assertEquals(
					"java.lang.NullPointerException",
					e.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString()); // left in case we want to add
												// illegalarg exception for self
												// reference
			assertEquals("java.lang.IllegalArgumentException", e.toString());
		} catch (Exception e) {
			fail();
		}

		// test adding self (consider adding illegalarg exception)
		try {
			peer.addPeer(peer);
		} catch (NullPointerException e) {
			assertEquals(
					"java.lang.NullPointerException",
					e.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString()); // left in case we want to add
												// illegalarg exception for self
												// reference
			assertEquals("java.lang.IllegalArgumentException", e.toString());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetKey() {
		// basic test
		assertEquals("key1", peer.getKey());
		assertEquals(key1, thing.getKey());
	}

	@Test
	public void testGetData() {
		// basic test
		assertTrue(25 == peer.getData());
		// complex tests handled under other testing functions
	}

	@Test
	public void testGetLevel() {
		// only basic test needed
		assertEquals(1, peer2.getLevel());
	}

	@Test
	public void testGetPeersAsCollection() {
		// prep
		peer.addPeer(peer1);
		peer.addPeer(peer2);
		peer.addPeer(peer3);
		// basic test
		Collection<Thing42orNull<String, Integer>> thingCollection = peer
				.getPeersAsCollection();
		boolean containsPeer2 = false;

		for (Thing42orNull<String, Integer> thingInList : thingCollection) {
			if (thingInList.getKey().equals("peerKey2")) {
				containsPeer2 = true;
				break;
			}
		}

		assertTrue(containsPeer2);
		assertEquals(3, thingCollection.size());

		// test empty/null collection
		thingCollection = peer2.getPeersAsCollection();
		assertTrue(thingCollection.isEmpty());
		assertEquals(0, thingCollection.size());

		// test self reference collection (should cycle, but doesn't?)
		peer.addPeer(peer);
		thingCollection = peer.getPeersAsCollection();
		boolean containsPeer = false;

		for (Thing42orNull<String, Integer> thingInList : thingCollection) {
			if (thingInList.getKey().equals("key1")) {
				containsPeer = true;
				break;
			}
		}

		assertTrue(containsPeer);
		assertEquals(4, thingCollection.size());
	}

	@Test
	public void testGetPoolAsList() {
		// prep
		peer.appendToPool(peer1);
		peer.appendToPool(peer2);
		peer.appendToPool(peer3);
		// basic test
		List<Thing42orNull<String, Integer>> thingList = peer.getPoolAsList();

		assertEquals(3, thingList.size());
		assertTrue(thingList.contains(peer3));

		// test empty/null List
		thingList = peer2.getPoolAsList();
		assertEquals(0, thingList.size());
		assertTrue(thingList.isEmpty());

		// test self reference (should cycle, but doesn't?)
		peer.appendToPool(peer);
		thingList = peer.getPoolAsList();
		assertEquals(4, thingList.size());
		assertTrue(thingList.contains(peer));
	}

	@Test
	public void testRemoveFromPool() {
		// basic test
		peer.appendToPool(peer1);
		peer.appendToPool(peer2);
		peer.appendToPool(peer3);

		assertTrue(peer.removeFromPool(peer2));
		List<Thing42orNull<String, Integer>> thingList = peer.getPoolAsList();

		assertTrue(!thingList.contains(peer2));

		// attempt to remove null
		try {
			peer.removeFromPool(null);
		} catch (NullPointerException e) {
			assertEquals(
					"java.lang.NullPointerException",
					e.toString());
		} catch (Exception e) {
			fail();
		}

		// attempt to remove member that doesn't exist
		assertTrue(!peer.removeFromPool(peer2));
		assertTrue(!thingList.contains(peer2));
	}

	@Test
	public void testRemovePeer() {
		// prep
		peer.addPeer(peer1);
		peer.addPeer(peer2);
		peer.addPeer(peer3);

		// basic test
		assertTrue(peer.removePeer(peer3));
		Collection<Thing42orNull<String, Integer>> thingList = peer
				.getPeersAsCollection();
		boolean containsPeer3 = false;

		for (Thing42orNull<String, Integer> thingInList : thingList) {
			if (thingInList.getKey().equals("peerKey3")) {
				containsPeer3 = true;
				break;
			}
		}

		assertFalse(containsPeer3);

		// attempt to remove null
		try {
			peer.removePeer(null);
		} catch (NullPointerException e) {
			assertEquals(
					"java.lang.NullPointerException",
					e.toString());
		} catch (Exception e) {
			fail();
		}

		// attempt to remove member that doesn't exist
		assertFalse(peer.removePeer(peer3));
	}

}
