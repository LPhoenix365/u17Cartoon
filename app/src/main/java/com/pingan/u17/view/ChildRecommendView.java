package com.pingan.u17.view;

import com.pingan.u17.bean.HomePageBean;
import com.pingan.u17.bean.UpdateBean;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public interface ChildRecommendView extends BaseView {
    void  getHomePageData(HomePageBean homePageBean);
    void  hasNewVersion(UpdateBean updateBean);
}
