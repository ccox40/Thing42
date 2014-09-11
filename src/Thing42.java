
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Thing42<K, D> implements Thing42orNull<K, D> {

    private final K key;
    private final long level;
    private final Collection<Thing42orNull<K, D>> peers;
    private final List<Thing42orNull<K, D>> pool;
    private D data;

    public Thing42(K key, long level, D data) {
        this.key = key;
        this.level = level;
        this.data = data;

        // Peers is not required to be ordered, but this makes for the simplest
        // implementation.
        peers = new LinkedList<Thing42orNull<K, D>>();
        // Pool must be an ordered list.
        pool = new LinkedList<Thing42orNull<K, D>>();
    }

    /**
     * Add a peer to this object.
     *
     * @param newPeer the peer to be added
     * @throws NullPointerException if the specified peer is null
     */
    @Override
    public void addPeer(Thing42orNull<K, D> newPeer) throws NullPointerException {
        if(newPeer == null) {
            throw new NullPointerException();
        }

        peers.add(newPeer);
    }

    /**
     * Append a member to the pool of this object.
     *
     * @param newMember the object to be appended to the pool
     * @throws NullPointerException if the specified item is null
     */
    @Override
    public void appendToPool(Thing42orNull<K, D> newMember) throws NullPointerException {
        if(newMember == null) {
            throw new NullPointerException();
        }

        pool.add(newMember);
    }

    /**
     * Access the data of this Thing42.
     *
     * @return the data of this object
     */
    @Override
    public D getData() {
        return data;
    }

    /**
     * Access the key of this Thing42.
     *
     * @return the key of this object
     */
    @Override
    public K getKey() {
        return key;
    }

    /**
     * Access the level of this Thing42.
     *
     * @return the level of this object
     */
    @Override
    public long getLevel() {
        return level;
    }

    /**
     * Access a peer matching the specified key.
     *
     * @param key the search key
     * @return any peer known by this object that matches the given key; null if
     * no match
     */
    @Override
    public Thing42orNull<K, D> getOnePeer(K key) {
        Thing42orNull<K, D> result = null;

        for(Thing42orNull<K, D> thing : peers) {
            if(thing.getKey().equals(key)) {
                result = thing;
            }
        }

        return result;
    }

    /**
     * Access all peers.
     *
     * @return all peers known by this object; if no peers then returns a
     * collection with size() == 0.
     */
    @Override
    public Collection<Thing42orNull<K, D>> getPeersAsCollection() {
        return peers;
    }

    ;

    /**
     * Access all peers matching the specified key.
     *
     * @param key the search key.
     * @return all peers known by this object that match the given key; if no
     *         peer matches then returns a collection with size() == 0.
     */
    @Override
    public Collection<Thing42orNull<K, D>> getPeersAsCollection(K key) {
        return peers.stream()
                .filter((thing) -> (thing.getKey() == key))
                .collect(Collectors.toList());
    }

    /**
     * Access all members of the pool.
     *
     * @return all members of the pool known by this object; if no members then
     * returns a List with size() == 0.
     */
    @Override
    public List<Thing42orNull<K, D>> getPoolAsList() {
        return pool;
    }

    ;

    /**
     * Remove a single instance of the specified object from this object's pool.
     *
     * @param member the member to be removed from the pool
     * @return true if a pool member was removed as a result of this call
     * @throws NullPointerException if the specified parameter is null
     */
    @Override
    public boolean removeFromPool(Thing42orNull<K, D> member) throws NullPointerException {
        if(member == null) {
            throw new NullPointerException();
        }

        return pool.remove(member);
    }

    /**
     * Remove a single instance of the specified peer from this object.
     *
     * @param peer the peer to be removed
     * @return Returns true if a peer was removed as a result of this call
     * @throws NullPointerException if the specified peer is null
     */
    @Override
    public boolean removePeer(Thing42orNull<K, D> peer) throws NullPointerException {
        if(peer == null) {
            throw new NullPointerException();
        }

        return peers.remove(peer);
    }

    /**
     * Modify the data of this Thing42.
     *
     * @param newData the updated data for this object
     */
    @Override
    public void setData(D newData) {
        data = newData;
    }

    /**
     * Determines whether or not the specified Object is equal to this
     * Thing42orNull. The specified Object is equal to this Thing42orNull if it
     * is an instance of Thing42; if its level is the same as this
     * Thing42orNull; and if its key, data, peers, and pool are the same as this
     * Thing42orNull via the equals predicate.
     *
     * @param obj an Object to be compared with this Thing42orNull.
     * @return true if obj is an instance of Thing42 and has the same values;
     * false otherwise.
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Thing42)) {
            return false;
        }

        Thing42<K, D> other = (Thing42<K, D>) obj;

        return this.key.equals(other.key) && this.data.equals(other.data)
                && this.peers.equals(other.peers)
                && this.pool.equals(other.pool) && this.level == other.level;
    }

    /**
     * Returns the hashcode for this Thing42orNull.
     *
     * @return the hashcode for this Thing42orNull
     */
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
}
