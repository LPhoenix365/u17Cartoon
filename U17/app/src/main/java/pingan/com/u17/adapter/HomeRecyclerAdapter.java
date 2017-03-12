package pingan.com.u17.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author sanshu
 * @data 2017/3/2 23:28
 * @ToDo ${TODO}
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter {
    private  final int titleModel=10;
    private  final int bannerModel=1;
    private  final int twoModel=2;
    private  final int threeModel=3;
    private  final int fourModel=4;
    private  final int fiveModel=5;
    private  final int sixModel=6;
    private  final int pureTwoModel=7;
    private  final int threeAndOneModel=8;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public int getItemViewType(int position) {
        int currentModel=-1;
        if ((position+1) % 2 == 0) {
            currentModel=titleModel;
            return currentModel;
        }
        //这个具体需要得到数据处在当前的位置
        switch(position){
            case 0:
                currentModel=bannerModel;
                break;
            case 2:
                currentModel=twoModel;
                break;
            case 4:
                currentModel=threeModel;
                break;
            case 6:
                currentModel=fourModel;
                break;
            case 8:
                currentModel=fiveModel;
                break;
            case 10:
                currentModel=sixModel;
                break;
            case 12:
                currentModel=pureTwoModel;
                break;


        }
        return super.getItemViewType(position);
    }




    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
