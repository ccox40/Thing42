import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author David Chang
 * @version 1
 * This is a JUNIT test class for Thing42.java.
 * Each method will test each thing42 method's functionality.
 */
public class Thing42Test{

    private Thing42<String,Integer> thing, peer1, peer2, peer3;

    @Before
    public void init() {
        thing = new Thing42<String, Integer>("key1", 1, 25);
        peer1 = new Thing42<String,Integer>("peerKey1", 1, 25);
        peer2 = new Thing42<String,Integer>("peerKey2", 1, 50);
        peer3 = new Thing42<String,Integer>("peerKey3", 1, 75);
    }

    @Test
    public void testAddPeer() {
        Thing42<String,Integer> peer = new Thing42<String,Integer>("peerKey1", 5, 10);

        thing.addPeer(peer);
        assertEquals(peer, thing.getOnePeer("peerKey1"));
    }

    @Test
    public void testGetKeyAndData(){
        assertEquals("key1", thing.getKey());
        assertTrue(25 == thing.getData());
    }

    @Test
    public void testGetPeerAsCollection(){

        thing.addPeer(peer1);
        thing.addPeer(peer2);
        thing.addPeer(peer3);

        Collection<Thing42orNull<String, Integer>> thingList = thing.getPeersAsCollection();
        boolean containsPeer2 = false;

        for (Thing42orNull<String, Integer> thingInList : thingList) {
            if (thingInList.getKey().equals("peerKey2")) {
                containsPeer2 = true;
                break;
            }
        }

        assertTrue(containsPeer2);
        assertEquals(3,thingList.size());
    }


    @Test
    public void testGetPoolAsList(){

        thing.appendToPool(peer1);
        thing.appendToPool(peer2);
        thing.appendToPool(peer3);

        List<Thing42orNull<String, Integer>> thingList = thing.getPoolAsList();

        assertEquals(3, thingList.size());
        assertTrue(thingList.contains(peer3));

    }

    @Test
    public void testRemoveFromPool(){
        thing.appendToPool(peer1);
        thing.appendToPool(peer2);
        thing.appendToPool(peer3);

        thing.removeFromPool(peer2);
        List<Thing42orNull<String, Integer>> thingList = thing.getPoolAsList();

        assertTrue(!thingList.contains(peer2));
    }

    @Test
    public void testRemovePeer(){
        thing.addPeer(peer1);
        thing.addPeer(peer2);
        thing.addPeer(peer3);

        thing.removePeer(peer3);
        Collection<Thing42orNull<String, Integer>> thingList = thing.getPeersAsCollection();
        boolean containsPeer3 = false;

        for (Thing42orNull<String, Integer> thingInList : thingList) {
            if (thingInList.getKey().equals("peerKey3")) {
                containsPeer3 = true;
                break;
            }
        }

        assertFalse(containsPeer3);
    }

    @Test
    public void testGetLevel(){
        assertEquals(1, peer2.getLevel());
    }

    @Test
    public  void testDataSet(){
        thing.setData(145555);
        assertTrue(145555 == thing.getData());
    }
}
