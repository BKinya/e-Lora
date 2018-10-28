package utils;

import model.HitsList;

public interface PayLoadResponsesCallbacks {

    public void onSuccess(HitsList hitsList);

    public void onFailure(Throwable throwable);
}
