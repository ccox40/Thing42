import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors; // This does not exist in Java 7 - consider replacing

public class Thing42<K, D> implements Thing42orNull<K, D> {
	private final K key;
	private final long level;

<<<<<<< HEAD
    /**
     * Add a peer to this object.
     *
     * @param newPeer The peer to be added.
     * @throws NullPointerException If newPeer is null.
     */
    @Override
    public void addPeer(Thing42orNull<K, D> newPeer) throws NullPointerException {
        if(newPeer == null) throw new NullPointerException();
        
        peers.add(newPeer);
    }

    /**
     * Append a member to the pool of this object.
     * 
     * @param newMember The object to be appended to the pool.
     * @throws NullPointerException If newMember is null.
     */
    @Override
    public void appendToPool(Thing42orNull<K, D> newMember) throws NullPointerException {
        if(newMember == null) throw new NullPointerException();
        
        pool.add(newMember);
    }
=======
	private final Collection<Thing42orNull<K, D>> peers;
	private final List<Thing42orNull<K, D>> pool;

	private D data;
>>>>>>> FETCH_HEAD

	public Thing42(K key, long level, D data) {
		this.key = key;
		this.level = level;
		this.data = data;

		// Peers is not required to be ordered, but this makes for the simplest
		// implementation.
		peers = new LinkedList<>();
		// Pool must be an ordered list.
		pool = new LinkedList<>();
	}

	/**
	 * Add a peer to this object.
	 *
	 * @param newPeer
	 *            The peer to be added.
	 * @throws NullPointerException
	 *             If newPeer is null.
	 */
	@Override
	public void addPeer(Thing42orNull<K, D> newPeer)
			throws IllegalArgumentException {
		if (newPeer == null)
			throw new IllegalArgumentException();

		peers.add(newPeer);
	}

	/**
	 * Append a member to the pool of this object.
	 * 
	 * @param newMember
	 *            The object to be appended to the pool.
	 * @throws NullPointerException
	 *             If newMember is null.
	 */
	@Override
	public void appendToPool(Thing42orNull<K, D> newMember)
			throws IllegalArgumentException {
		if (newMember == null)
			throw new IllegalArgumentException();

		pool.add(newMember);
	}

	/**
	 * Access the data of this object.
	 * 
	 * @return The data of this object.
	 */
	@Override
	public D getData() {
		return data;
	}

	/**
	 * Access the key of this object.
	 * 
	 * @return The key of this object.
	 */
	@Override
	public K getKey() {
		return key;
	}

	/**
	 * Access the level of this object.
	 * 
	 * @return The level of this object.
	 */
	@Override
	public long getLevel() {
		return level;
	}

	/**
	 * Access a peer matching the specified key.
	 * 
	 * @param key
	 *            The search key.
	 * @return Any one peer this object knows with a matching key; null if no
	 *         match is found.
	 */
	@Override
	public Thing42orNull<K, D> getOnePeer(K key) {
		Thing42orNull result = null;

		for (Thing42orNull<K, D> thing : peers) {
			if (thing.getKey().equals(key)) {
				result = thing;
			}
		}

		return result;
	}

	/**
	 * Access all peers.
	 * 
	 * @return All peers known by this object. An empty collection is returned
	 *         if there are no known peers.
	 */
	@Override
	public Collection<Thing42orNull<K, D>> getPeersAsCollection() {
		return peers;
	};

	/**
	 * Access all peers with a specified key.
	 * 
	 * @param key
	 *            The key to match.
	 * @return All peers with a matching key. An empty collection is returned if
	 *         there are no known peers.
	 */
	@Override
    public Collection<Thing42orNull<K, D>> getPeersAsCollection(K key) {
        return peers.stream()
                    .filter((thing) -> (thing.getKey() == key))
                    .collect(Collectors.toList());
    }

<<<<<<< HEAD
     /**
     * Access all members of the pool.
     * @return All known members of this object's pool. An empty collection is
     *         returned if there are no pool members.
     */
    @Override
    public List<Thing42orNull<K, D>> getPoolAsList() {
        return pool;
    };

    /**
     * Remove a member from this object's pool.
     * @param member The member to be removed from the pool.
     * @return Returns true if a pool member was removed as a result of this
     *         call.
     * @throws NullPointerException If member is null.
     */
    @Override
    public boolean removeFromPool(Thing42orNull member) throws NullPointerException {
        if(member == null) throw new NullPointerException();
        
        return pool.remove(member);
    }

    /**
     * Remove a peer from this object.
     * @param peer The peer to be removed from this object.
     * @return Returns true if a peer was removed as a result of this call.
     * @throws NullPointerException If peer is null.
     */
    @Override
    public boolean removePeer(Thing42orNull peer) throws NullPointerException {
        if(peer == null) throw new NullPointerException();
        
        return peers.remove(peer);
    }
=======
	/*
	 * Consider replacing the above code as Collectors does not exist in Java 7.
	 * this code may be a valid substitue: 
	 * Collection<Thing42orNull> 
	 * output =	 * Collections.emptySet(); 
	 * for (Thing42orNull peer : peers){ 
	 * if	 * (peer.getKey() == key) 
	 * output.add(peer); } 
	 * return output;
	 */

	/**
	 * Access all members of the pool.
	 * 
	 * @return All known members of this object's pool. An empty collection is
	 *         returned if there are no pool members.
	 */
	@Override
	public List<Thing42orNull<K, D>> getPoolAsList() {
		return pool;
	};
>>>>>>> FETCH_HEAD

	/**
	 * Remove a member from this object's pool.
	 * 
	 * @param member
	 *            The member to be removed from the pool.
	 * @return Returns true if a pool member was removed as a result of this
	 *         call.
	 * @throws NullPointerException
	 *             If member is null.
	 */
	@Override
	public boolean removeFromPool(Thing42orNull member)
			throws IllegalArgumentException {
		if (member == null)
			throw new IllegalArgumentException();

		return pool.remove(member);
	}

	/**
	 * Remove a peer from this object.
	 * 
	 * @param peer
	 *            The peer to be removed from this object.
	 * @return Returns true if a peer was removed as a result of this call.
	 * @throws NullPointerException
	 *             If peer is null.
	 */
	@Override
	public boolean removePeer(Thing42orNull peer)
			throws IllegalArgumentException {
		if (peer == null)
			throw new IllegalArgumentException();

		return peers.remove(peer);
	}

	/**
	 * Modify the data of this object.
	 * 
	 * @param newData
	 *            The updated data for this object.
	 */
	@Override
	public void setData(D newData) {
		data = newData;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Thing42))
			return false;

		Thing42<K, D> other = (Thing42<K, D>) o;

		return this.key.equals(other.key) && this.data.equals(other.data)
				&& this.peers.equals(other.peers)
				&& this.pool.equals(other.pool) && this.level == other.level;
	}

	@Override
	public int hashCode() {
		// This was generated entirely by NetBeans.
		int hash = 5;
		hash = 19 * hash + Objects.hashCode(this.key);
		hash = 19 * hash + (int) (this.level ^ (this.level >>> 32));
		hash = 19 * hash + Objects.hashCode(this.peers);
		hash = 19 * hash + Objects.hashCode(this.pool);
		hash = 19 * hash + Objects.hashCode(this.data);
		return hash;
	}

	@Override
	public String toString() {
		return String
				.format("(Key: %s, Level: %d, Data: %s)", key, level, data);
	}
}
