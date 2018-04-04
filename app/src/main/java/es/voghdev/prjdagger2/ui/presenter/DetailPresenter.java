package es.voghdev.prjdagger2.ui.presenter;

import es.voghdev.prjdagger2.global.AndroidResLocator;
import es.voghdev.prjdagger2.global.ResLocator;

public class DetailPresenter extends Presenter<DetailPresenter.MVPView> {
    ResLocator resLocator;


    public DetailPresenter(AndroidResLocator resLocator) {
        this.resLocator = resLocator;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public interface MVPView {

    }
}

