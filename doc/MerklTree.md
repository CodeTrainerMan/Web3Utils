# Java Merkle Tree

This project implements a Java version of Merkle Tree, aiming to be consistent with the Merkle Tree algorithm in Solidity and providing proof verification functionality.

## Algorithm Overview

Merkle Tree is a tree-like data structure used to verify the integrity and consistency of data. It divides the data into fixed-sized blocks and performs hash calculations on these blocks to build a tree-like structure layer by layer, ultimately generating a unique root hash value. By verifying the hash values on the path between the leaf nodes and the root hash, it is possible to verify whether specific data belongs to the Merkle Tree.

## Implementation Details

The Java Merkle Tree implementation consists of the following components:

- **Data Structure:** The `MerkleTree` class represents the nodes of the Merkle Tree, including the hash value of the node, references to the left and right child nodes, and the parent node.

- **Tree Construction:** The Merkle Tree is constructed based on the input data list using the constructor and the `buildTree` method. During the construction process, the SHA-256 hash algorithm is used to compute the hash values of the data and generate parent nodes layer by layer.

- **Proof Verification:** The `verifyProof` method is provided to verify whether the given data and proof are consistent with the root hash of the Merkle Tree. The method tracks the hash values on the verification path, computes the combined hash values step by step, and compares them with the given proof.


