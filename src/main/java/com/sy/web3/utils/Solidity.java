package com.sy.web3.utils;

import org.web3j.crypto.Hash;

/**
 * TODO
 *
 * @Description
 * @Author william
 * @Date 2023/7/9 20:36
 **/
public class Solidity {
    public static String packedKeccak256(String address, Integer quantity){
        String quantity64 = String.format("%0" + 64 + "x", quantity);;
        return Hash.sha3(address+quantity64);

    }
}
