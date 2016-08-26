package com.example.renhao.wevolunteer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.company.CompanyViewDto;
import com.example.model.dictionary.DictionaryListDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseFragmentV4;
import com.example.renhao.wevolunteer.event.OrganizationDetailEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/12 9:33
 * 修改备注：
 */
public class OrganizationPage1 extends BaseFragmentV4 {
    private static final String TAG = "OrganizationPage1";
    @Bind(R.id.tv_organization_addressName)
    TextView mTvAddressName;
    @Bind(R.id.tv_organization_address)
    TextView mTvAddress;
    @Bind(R.id.tv_organization_chargeName)
    TextView mTvChargeName;
    @Bind(R.id.tv_organization_charge)
    TextView mTvCharge;
    @Bind(R.id.tv_organization_phoneName)
    TextView mTvPhoneName;
    @Bind(R.id.tv_organization_phone)
    TextView mTvPhone;
    @Bind(R.id.tv_organization_honourName)
    TextView mTvHonourName;
    @Bind(R.id.tv_organization_honour)
    TextView mTvHonour;
    @Bind(R.id.tv_organization_createName)
    TextView mTvCreateName;
    @Bind(R.id.tv_organization_create)
    TextView mTvCreate;
    @Bind(R.id.tv_organization_serverName)
    TextView mTvServerName;
    @Bind(R.id.tv_organization_server)
    TextView mTvServer;
    @Bind(R.id.tv_organization_detailName)
    TextView mTvDetailName;
    @Bind(R.id.relative_organization_detailName)
    RelativeLayout mRelativeDetailName;
    @Bind(R.id.tv_organization_detail)
    TextView mTvDetail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_organization_page1, container, false);
        ButterKnife.bind(this, v);
        EventBus.getDefault().register(this);
        return v;
    }

    @Subscribe
    public void onEventMainThread(OrganizationDetailEvent event) {
        CompanyViewDto dto = event.getCompanyViewDto();
        mTvAddress.setText(TextUtils.isEmpty(dto.getAddr()) ? "" : dto.getAddr());
        mTvCharge.setText(TextUtils.isEmpty(dto.getPerson()) ? "" : dto.getPerson());
        String phone = (TextUtils.isEmpty(dto.getTel()) ? "" : (dto.getTel()))
                + (TextUtils.isEmpty(dto.getMobile()) ? "" : ("\n" + dto.getMobile()));
        mTvPhone.setText(phone);
        //组织荣耀
        mTvHonour.setText(dto.getHonors());

        mTvCreate.setText(dto.getCreateTime());

       /* AppActionImpl.getInstance(getActivity()).dictionaryQueryDetailDefault("ActivityType",
                dto.getServiceType(),
                new ActionCallbackListener<DictionaryViewDto>() {
                    @Override
                    public void onSuccess(DictionaryViewDto data) {
                        if (data != null)
                            mTvServer.setText(data.getName());
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });*/
        final String[] types = dto.getServiceType().split(",");
        AppActionImpl.getInstance(getActivity()).dictionaryQueryDefault("ActivityType", "",
                new ActionCallbackListener<List<DictionaryListDto>>() {
                    @Override
                    public void onSuccess(List<DictionaryListDto> data) {
                        String type = "";
                        for (int i = 0; i < data.size(); i++) {
                            for (int j = 0; j < types.length; j++) {
                                if (data.get(i).getCode().equals(types[j])) {
                                    type += "  " + data.get(i).getName();
                                }
                            }
                        }
                        mTvServer.setText(type);
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });


        mTvDetail.setText(dto.getDescription());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
