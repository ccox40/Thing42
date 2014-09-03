
import java.util.Collection;
import java.util.List;

public interface Thing42orNull<K, D> {
    /**
     * Add a peer to this object.
     *
     * @param newPeer The peer to be added.
     * @throws NullPointerException If newPeer is null.
     */
    void addPeer(Thing42orNull<K, D> newPeer) throws NullPointerException;
    /**
     * Append a member to the pool of this object.
     * 
     * @param newMember The object to be appended to the pool.
     * @throws NullPointerException If newMember is null.
     */
    void appendToPool(Thing42orNull<K, D> newMember) throws NullPointerException;
    /**
     * Access the data of this object.
     * @return The data of this object.
     */
    D getData();
    /**
     * Access the key of this object.
     * @return The key of this object.
     */
    K getKey();
    /**
     * Access the level of this object.
     * @return The level of this object.
     */
    long getLevel();
    /**
     * Access a peer matching the specified key.
     * @param key The search key.
     * @return Any one peer this object knows with a matching key; null if no
     *         match is found.
     */
    Thing42orNull getOnePeer(K key);
    /**
     * Access all peers.
     * @return All peers known by this object. An empty collection is returned
     *         if there are no known peers.
     */
    Collection<Thing42orNull<K, D>> getPeersAsCollection();
    /**
     * Access all peers with a specified key.
     * @param key The key to match.
     * @return All peers with a matching key. An empty collection is returned if
     *         there are no known peers.
     */
    Collection<Thing42orNull<K, D>> getPeersAsCollection(K key);
    /**
     * Access all members of the pool.
     * @return All known members of this object's pool. An empty collection is
     *         returned if there are no pool members.
     */
    List<Thing42orNull<K, D>> getPoolAsList();
    /**
     * Remove a member from this object's pool.
     * @param member The member to be removed from the pool.
     * @return Returns true if a pool member was removed as a result of this
     *         call.
     * @throws NullPointerException If member is null.
     */
    boolean removeFromPool(Thing42orNull<K, D> member) throws NullPointerException;
    /**
     * Remove a peer from this object.
     * @param peer The peer to be removed from this object.
     * @return Returns true if a peer was removed as a result of this call.
     * @throws NullPointerException If peer is null.
     */
    boolean removePeer(Thing42orNull<K, D> peer) throws NullPointerException;
    /**
     * Modify the data of this object.
     * @param newData The updated data for this object.
     */
    void setData(D newData);
}
