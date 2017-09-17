package com.spectrus.chemport.AppData;

/**
 * Activity Information to signal Loading setup
 */
public class ActivityData {

    // Flashcard Data
    public int [] cards_Num = new int [] { 15, 25, 40, 50, 75, 100 };
    public int [] cards_Ran = new int [] { 18, 30, 54, 80, 90, 118 };

    // Multiple Choice Data
    public int [] mc_Num_EleSym = new int [] { 15, 25, 50, 75, 100 };
    public int [] mc_Ran_EleSym = new int [] { 25, 50, 75, 100, 118 };

    // Various Modes
    // 0: 0 = pre-2016, 1 = updated 2016
    // 1: MC Game Mode: 0 = Leisure, 1= Timed
    // 2:
    public int [] gameMode = new int [] { 0, 0, 0 };

    // Activity Default Settings
    public String activityValue = "ACTIVITY";
    public boolean default_mode = true;
    public int default_level = 0;

    // Reset Activity Data
    public void resetData(){
        activityValue = "ACTIVITY";
        default_mode = true;
        default_level = 0;

        // Specific Data
        gameMode = new int [] { 0, 0, 0 };
    }
}
