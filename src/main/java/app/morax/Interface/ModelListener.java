package app.morax.Interface;

import app.morax.Model.Base.MainModel;

public interface ModelListener {
    void update();
    void setModel(MainModel model);
}
