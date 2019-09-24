import request from '@/utils/request'

export default {

  // 根据三级分类id获取属性列表
  getSpuList(catalog3Id) {
    return request({
      url: 'spuList/' + catalog3Id,
      method: 'get'
    })
  },

  // 保存Spu
  saveSpuInfo(spuForm) {
    return request({
      url: 'saveSpuInfo',
      method: 'post',
      data: spuForm
    })
  },

  // 获取基本销售属性列表
  getBaseSaleAttrList(catalog3Id) {
    return request({
      url: 'baseSaleAttrList/' + catalog3Id,
      method: 'post',
      data: catalog3Id
    })
  },

  // 根据spuId获取销售属性列表
  getSpuSaleAttrList(spuId) {
    return request({
      url: 'spuSaleAttrList?spuId=' + spuId,
      method: 'get'
    })
  },

  // 根据spuId获取图片列表
  getSpuImageList(spuId) {
    return request({
      url: 'spuImageList?spuId=' + spuId,
      method: 'get'
    })
  }
}
