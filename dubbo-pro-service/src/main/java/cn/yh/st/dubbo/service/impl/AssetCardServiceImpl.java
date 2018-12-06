package cn.yh.st.dubbo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import cn.yh.st.dubbo.dao.AssetCardDao;
import cn.yh.st.dubbo.domain.AssetCardModel;
import cn.yh.st.dubbo.facade.AssetCardService;

@Service("assetCardService")
public class AssetCardServiceImpl implements AssetCardService {

	@Override
	public int testSaveAssetCard(AssetCardModel model) {
		return 0;
	}

	@Override
	public int confirmMysqlSaveAssetCard(AssetCardModel model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cancelMysqlSaveAssetCard(AssetCardModel model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void processMongo(AssetCardModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void confirmMongoSaveAssetCard(AssetCardModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelMongoSaveAssetCard(AssetCardModel model) {
		// TODO Auto-generated method stub

	}

}
