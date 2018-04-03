package es.voghdev.prjdagger2.ui.presenter;

import es.voghdev.prjdagger2.ui.presenter.Presenter;
import es.voghdev.prjdagger2.repository.UserRepository;
import es.voghdev.prjdagger2.ui.ResLocator;

public class DetailPresenter extends Presenter<DetailPresenter.MVPView, DetailPresenter.Navigator> {
    ResLocator resLocator;


    public DetailPresenter(ResLocator resLocator) {
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

    public interface MVPView extends AbsMVPView {

    }

    public interface Navigator extends AbsNavigator {

    }
}

