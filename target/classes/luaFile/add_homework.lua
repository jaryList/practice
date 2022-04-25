-- KEYS[1] 以综合评分做为分数排名的有序集合key
-- ARGV[1] 作业id
-- ARGV[2] 综合评分

-- KEYS[2] 以下单时间时间戳做为分数排名的有序集合key
-- ARGV[3] 下单时间时间戳

-- KEYS[3] 作业hash表的key
-- ARGV[4] 作业详情

-- 写综合评分有序集合
local add_composite_result = redis.call('ZADD', KEYS[1], ARGV[2], ARGV[1])
-- 写时间戳有序集合
local add_timestamp_result = redis.call('ZADD', KEYS[2], ARGV[3], ARGV[1])
-- 写作业详情
local add_homework_detail_result = redis.call('HSET',KEYS[3], ARGV[1], ARGV[4])
-- 都写成功了则返回成功，否则失败
if add_composite_result and add_timestamp_result and add_homework_detail_result then
    return "success"
else
    return "fail"
end
