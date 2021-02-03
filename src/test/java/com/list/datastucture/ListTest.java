package com.list.datastucture;

import com.list.datastructure.SingleLinkedList;
import org.junit.Test;

import java.util.LinkedList;

public class ListTest {

    @Test
    public void singleLikedListAddTest(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<String>();
        stringSingleLinkedList.add("1");
        stringSingleLinkedList.add("2");
        stringSingleLinkedList.add("3");
        stringSingleLinkedList.remove();
        stringSingleLinkedList.remove();
        stringSingleLinkedList.remove();
        stringSingleLinkedList.remove();
        //LinkedList
    }
}
