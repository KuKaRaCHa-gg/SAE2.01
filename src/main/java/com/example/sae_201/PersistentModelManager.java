package com.example.sae_201;

import gameModel.MyGames;

public interface PersistentModelManager {

    public void save(MyGames model);

    public MyGames load();

}