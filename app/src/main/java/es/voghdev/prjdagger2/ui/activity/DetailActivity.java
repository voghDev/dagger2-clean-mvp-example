package es.voghdev.prjdagger2.ui.activity;

import android.os.Bundle;

import es.voghdev.prjdagger2.ui.activity.BaseActivity;
import es.voghdev.prjdagger2.R;
import es.voghdev.prjdagger2.global.AndroidResLocator;
import es.voghdev.prjdagger2.ui.presenter.DetailPresenter;

public class DetailActivity extends BaseActivity implements DetailPresenter.MVPView {
    DetailPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        presenter = new DetailPresenter(new AndroidResLocator(this));
        presenter.setView(this);
        //presenter.setNavigator(this);

        presenter.initialize();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }
}