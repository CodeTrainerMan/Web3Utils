package com.sy.web3.utils;

import org.web3j.crypto.Hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Date 2023/7/8 0:22
 **/
public class MerklTree {
    private List<String> leaves;
    private List<List<String>> layers;

    /**
     *
     * @param leafList
     * @param sort
     */
    public MerklTree(List<String> leafList,boolean sort) {
        if(sort==true){
            sortList(leafList);
        }
        this.leaves = remove0x(leafList);
        this.layers = new ArrayList<>();
        processLeaves(this.leaves,sort);

    }
    /*
     * TODO
     *
     * @Description
     * @Author Administrator
     * @Date 2023/7/7 23:42
     **/
    public MerklTree(List<String> leafList) {
        /*
         * TODO
         *
         * @Description
         * @Param leafList
         * @Author Administrator
         * @Date 2023/7/7 23:43
         **/
        this.leaves = remove0x(leafList);
        this.layers = new ArrayList<>();
        processLeaves(this.leaves,false);
    }

    /**
     *
     * @param leafList
     * @param sort
     */
    private void processLeaves(List<String> leafList,boolean sort){
        try {
            this.layers.add(leafList);
            List<String> nodeList = leafList;
            while (nodeList.size()>1) {
                int layerIndex = this.layers.size();
                this.layers.add(new ArrayList<>());
                for (int i = 0; i < nodeList.size(); i += 2) {
                    //if i is the last one, it means the nodeList amount is odd
                    if(i + 1 == nodeList.size()){
                        this.layers.get(layerIndex).add(nodeList.get(i));
                        continue;
                    }

                    String left = nodeList.get(i);
                    String right = nodeList.get(i + 1);

                    String combine = Hash.sha3(left+right).substring(2);
                    this.layers.get(layerIndex).add(combine);
                }
                if (sort==true){
                    sortList(this.layers.get(layerIndex));
                }
                nodeList = this.layers.get(layerIndex);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /*
     * sort list
     *
     * */
    private static void sortList(List<String> leafList) {


        Collections.sort(leafList, ((o1, o2) -> {
            int startIndex = 0;
            if(o1.startsWith("0x")&&o2.startsWith("0x")){
                startIndex = 2;
            }
            if (Integer.parseInt(o1.substring(startIndex, startIndex+2), 16) > Integer.parseInt(o2.substring(startIndex, startIndex+2), 16)) {
                return 1;
            }
            if (Integer.parseInt(o1.substring(startIndex, startIndex+2), 16) == Integer.parseInt(o2.substring(startIndex, startIndex+2), 16)) {
                for (int i = startIndex+2; i < o1.length(); i += 2) {
                    if (Integer.parseInt(o1.substring(i, i + 2), 16) > Integer.parseInt(o2.substring(i, i + 2), 16)) {
                        return 1;
                    } else if (Integer.parseInt(o1.substring(i, i + 2), 16) == Integer.parseInt(o2.substring(i, i + 2), 16)) {
                        continue;
                    } else {
                        return -1;
                    }
                }
                return 0;
            }
            return -1;
        }));
    }
    /*
     * findLeavesIndex
     * */
    public Integer findLeavesIndex(List<String> leaves,Integer intIndex, String hashLeaf){
        if(intIndex == null){
            for (int i = 0; i < leaves.size(); i++) {
                if(hashLeaf.equals(leaves.get(i))){
                    return i;
                }
            }
            return null;
        }else {
            if(!hashLeaf.equals(leaves.get(intIndex))){
                System.out.printf("index not match%s-%s\n",leaves.get(intIndex), hashLeaf);
                return null;
            }
            return intIndex;
        }
    }
    /*
     * getProof
     * */
    public List<String> getProof(String leaf, Integer intIndex){

        List<String> proofList = new ArrayList<>();

        String hashLeaf = leaf.substring(2);

        Integer leavesIndex = findLeavesIndex(this.leaves, intIndex, hashLeaf);

        if(leavesIndex <= -1){
            System.out.print("not found in leaves\n");
            return null;
        }
        //root node is not needed in proof
        for (int i = 0; i < this.layers.size() - 1; i++) {
            List<String> layer = this.layers.get(i);
            boolean isRightNode = leavesIndex % 2 == 1;
            int pairIndex = isRightNode? leavesIndex - 1 : (leavesIndex == layer.size()-1 ? leavesIndex: leavesIndex + 1);
            if(pairIndex<layer.size()){
                if(!(leavesIndex==(layer.size()-1)&&!isRightNode)){
                    proofList.add(layer.get(pairIndex));
                }
            }
            if(this.layers.size() != (i+1)){
                if(isRightNode){
                    hashLeaf =  Hash.sha3(layer.get(pairIndex)+layer.get(leavesIndex)).substring(2);
                }else {
                    hashLeaf =  Hash.sha3(layer.get(leavesIndex)+layer.get(pairIndex)).substring(2);
                }
                if(leavesIndex==(layer.size()-1)&&!isRightNode){
                    hashLeaf = layer.get(leavesIndex);
                }
                leavesIndex = findLeavesIndex(this.layers.get(i+1), intIndex, hashLeaf);
            }

        }
        return proofList;
    }

    /**
     *
     * @param leaf
     * @param intIndex
     * @return
     */
    public List<String> getHexProof(String leaf, Integer intIndex) {
        List<String> proof = getProof(leaf, intIndex);
        List<String> collect = proof.stream().map(x -> {
                    return "0x" + x;
                }
        ).collect(Collectors.toList());
        return collect;
    }
    /*getRoot
     * */
    public String getRoot() {
        if(this.layers.size() == 0){
            return new String();
        }
        return this.layers.get(this.layers.size()-1).get(0);
    }
    /*
     *getHexRoot
     * */
    public String getHexRoot(){
        return "0x"+getRoot();
    }
    /*
     *verify
     * */
    public boolean verify(List<String> proof, String root, String leaf){
        String computedHash = leaf.substring(2);
        ArrayList<String> verifyList = new ArrayList<>();

        try {
            for (int i = 0; i < proof.size(); i++) {
                String proofElement = proof.get(i);
                verifyList.add(computedHash);
                verifyList.add(proofElement);
                sortList(verifyList);
                if(proofElement.equals(computedHash)){
                    computedHash = proofElement;
                }else {
                    computedHash = Hash.sha3(verifyList.get(0) + verifyList.get(1)).substring(2);
                }
                verifyList.clear();
            }
            return computedHash.equals(root);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    /*
     * remove0x
     * */
    public static List<String> remove0x(List<String> leafList) {
        List<String> collect = leafList.stream().filter(x -> x != null && x != "").map(x -> {
            if (x.startsWith("0x")) {
                return x.substring(2);
            }
            return x;
        }).collect(Collectors.toList());
        return collect;
    }


}
