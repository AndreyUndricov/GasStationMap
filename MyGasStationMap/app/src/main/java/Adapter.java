import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends AdapterView {
    public class ViewHolder extends RecyclerView.ViewHolder
    public Adapter(Context context) {
        super(context);
    }

    @Override
    public android.widget.Adapter getAdapter() {
        return null;
    }

    @Override
    public void setAdapter(android.widget.Adapter adapter) {

    }

    @Override
    public View getSelectedView() {
        return null;
    }

    @Override
    public void setSelection(int i) {

    }
}
