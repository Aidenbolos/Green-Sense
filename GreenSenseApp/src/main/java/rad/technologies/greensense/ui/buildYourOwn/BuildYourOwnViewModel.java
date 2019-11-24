package rad.technologies.greensense.ui.buildYourOwn;
//R.A.D. Technologies
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuildYourOwnViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BuildYourOwnViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}