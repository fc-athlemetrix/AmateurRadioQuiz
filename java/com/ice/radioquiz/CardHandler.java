package com.ice.radioquiz;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.ArrayList;
import java.util.Random;

public class CardHandler {
    private final ArrayList<Integer> excludeList;
    int arraySize;

    Random rand;

    ArrayList<Integer> excludeLIst;
    int currentPointer;
   boolean random = FALSE;

    public CardHandler(int i1, int i2) {
        arraySize = i1;
        currentPointer = i2;
        rand = new Random();

// Obtain a number between [0 - 49].
        int n = rand.nextInt(50);
        excludeList = new ArrayList<Integer>();
    }
    protected int nextCard() {

        if (random) {
            currentPointer = rand.nextInt(arraySize);
            while (checkForExclude(currentPointer) == TRUE) {
                currentPointer = rand.nextInt(arraySize);
            }
        }
        else {
            currentPointer++;
            while (checkForExclude(currentPointer) == TRUE) {
                currentPointer++;
            }
        }

       if (currentPointer < (arraySize - 1)) {
            ;
        } else
            currentPointer = 0;

        return currentPointer;

    }

    private boolean checkForExclude(int index) {
        if (excludeList.contains(currentPointer))
           return TRUE;
        else
            return FALSE;
    }

    protected int prevCard() {

        if (random) {
            currentPointer = rand.nextInt(arraySize);
            while (checkForExclude(currentPointer) == TRUE) {
                currentPointer = rand.nextInt(arraySize);
            }
        }
        else {
            currentPointer--;
            while (checkForExclude(currentPointer) == TRUE) {
                currentPointer--;
            }
        }

        if (currentPointer < 0)
            currentPointer = arraySize -1;

        return currentPointer;

    }

    protected void addCardToExcludeList(int i) {
        excludeList.add(i);
    }

    protected void setRandom() {
        random = TRUE;
      }

    protected void clearRandom() {
        random = FALSE;
      }
    }

