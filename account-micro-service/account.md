# account-micro-service
账号系统

## Currency

### Prepaid

`小明想购买商品，但是由于Currency+Bonus不足，需要Prepaid Currency，系统提供的Prepaid方式有：微信支付、支付宝支付、银联支付...
汇率Policy为：1￥ = 10c`

### Bonus

`小明通过完成系统Task，获得了系统发放的10c，小明可以在领取后的7天内（Policy）消费时作为抵扣，超过7天则无法使用了。`

### Exchange

`小明将Credit按照100 = 1c的比例兑换为Currency，并在30天内可以使用它，过期将不可用。`

### Payment

`小明可以用Currency去购买商品，购买时系统自动选择并优先消耗将要Expire的Bonus Currency，Bonus Currency可以拆分使用`


## Coupon

### 发放


## Credit

1. 获取积点
2. 积点兑换为*优惠券*或*奖励积分*

## VIP