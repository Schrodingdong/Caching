# Caching
This Repository contains the implementation of the following algorithms:

## Least Recent Used (LRU) caching strategy
A cache replacement algorithm that replaces cache when the space is full. The implementation aimes
to achieve an O(1) time complexity for the following operations:
- get(key): Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
- put(key, value): Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

## Dependencies
- Postgres db containing 10000 records
