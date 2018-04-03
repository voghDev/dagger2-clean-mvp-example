package es.voghdev.prjdagger2.ui.presenter;

import es.voghdev.prjdagger2.ui.ResLocator;
import es.voghdev.prjdagger2.ui.presenter.Presenter;
import es.voghdev.prjdagger2.repository.UserRepository;

import org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito.times;
import org.mockito.Mockito.verify;


public class DetailPresenterTest {


    @Mock
    ResLocator mockResLocator;

    @Mock
    DetailPresenter.Navigator mockNavigator;

    @Mock
    DetailPresenter.MVPView mockView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private DetailPresenter createMockedPresenter() {
        DetailPresenter presenter = new DetailPresenter(mockResLocator);
        presenter.setView(mockView);
        presenter.setNavigator(mockNavigator);
        return presenter;
    }
}
