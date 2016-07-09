package es.voghdev.prjdagger2.interactor;


public interface MainThread {
    void post(final Runnable runnable);
}