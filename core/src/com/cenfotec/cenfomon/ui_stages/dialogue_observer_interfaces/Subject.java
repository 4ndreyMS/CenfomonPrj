package com.cenfotec.cenfomon.ui_stages.dialogue_observer_interfaces;

public interface Subject {
    void addObserver(DialogueObserver dialogueObserver);
    void removeObserver(DialogueObserver dialogueObserver);
    void notifyObservers(int value);
}
