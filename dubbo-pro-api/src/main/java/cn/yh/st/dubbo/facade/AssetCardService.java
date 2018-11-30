package cn.yh.st.dubbo.facade;

import cn.yh.st.dubbo.domain.AssetCardModel;

public interface AssetCardService {
	/**
	 * 测试预保存资产(状态为待确认)
	 */
	int testSaveAssetCard(AssetCardModel model);

	/**
	 * 确认保存资产到mysql(状态为正常)
	 */
	int confirmMysqlSaveAssetCard(AssetCardModel model);

	/**
	 * 取消保存资产到msyql(更新状态为删除)
	 */
	int cancelMysqlSaveAssetCard(AssetCardModel model);

	/**
	 * 预保存资产到mongo(状态为待确认)
	 */
	void processMongo(AssetCardModel model);

	/**
	 * 确认保存资产到mongo(状态为正常)
	 */
	void confirmMongoSaveAssetCard(AssetCardModel model);

	/**
	 * 取消保存资产到mongo(更新状态为删除)
	 */
	void cancelMongoSaveAssetCard(AssetCardModel model);
}
