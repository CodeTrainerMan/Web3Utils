package com.sy.web3.utils;

import org.junit.Before;
import org.junit.Test;
import org.web3j.crypto.Hash;
import org.web3j.utils.Numeric;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.sy.web3.utils.Solidity.packedKeccak256;
import static org.junit.Assert.assertEquals;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Date 2023/7/8 0:22
 **/
public class MerklTreeTest {
    List<String> list;
    MerklTree merkleTree4Solidity;
//    @Before
    public void setUp() throws Exception {
        list = new ArrayList<>();

        list.add(packedKeccak256("0xfc3921042358aC9a4092C4506bD20C6d9744DA47",981));
        list.add(packedKeccak256("0x5d46Cc7813E97AC4379df8c1Fe30B8c875754aa0",711));
        list.add(packedKeccak256("0x9F7827F2EeD4E608d22057e03d6aAe593F0e47ae",9));
        list.add(packedKeccak256("0xA76f2B935C04Ab0D1c2EB16a4A7f410C656Cf645",1));
        list.add(packedKeccak256("0x5484B2df5674e2E3F9954C3972774fd2bF0A5326",170));
        list.add(packedKeccak256("0x03a6dfE7c7AA062F23B56657313571cD1CD4aDf9",1057));

        merkleTree4Solidity = new MerklTree(list, true);
        MerklTree merklTree = new MerklTree(list);
    }


    @Test
    public void findLeavesIndex() {
    }


    @Test
    public void getHexProof() {
        String root = merkleTree4Solidity.getHexRoot();
        assertEquals(root,"0x8c06e747aa414f1464660b7f1636f1091ee9784fa7cfc2b23ffede17b76c0f1b");
    }

    @Test
    public void getRoot() {
        String root = merkleTree4Solidity.getRoot();
        assertEquals(root,"8c06e747aa414f1464660b7f1636f1091ee9784fa7cfc2b23ffede17b76c0f1b");
    }

    @Test
    public void getHexRoot() {
    }


    @Test
    public void testVerify() {

        List<String> proof = merkleTree4Solidity.getProof(packedKeccak256("0xfc3921042358aC9a4092C4506bD20C6d9744DA47",981), null);
        List<String> hexProof = merkleTree4Solidity.getHexProof(packedKeccak256("0xfc3921042358aC9a4092C4506bD20C6d9744DA47", 981), null);
        String root = merkleTree4Solidity.getRoot();
        boolean verify = merkleTree4Solidity.verify(proof, root, packedKeccak256("0xfc3921042358aC9a4092C4506bD20C6d9744DA47",981));
        assertEquals(verify,true);

    }

    @Test
    public void getProof() {
    }

    @Test
    public void testGetHexProof() {

//        "0x13ef58b4d3d01ae20d394a1997d841ddb300d070966300484841aa9a3b195320"
    }

    @Test
    public void testGetHexRoot() {
      

    }
}
