-- KEYS[1] 以下单时间时间戳做为分数排名的有序集合key
-- ARGV[1] 清理数据时间戳最小分值
-- ARGV[2] 清理数据时间戳最大分值

-- KEYS[2] 以综合评分做为分数排名的有序集合key

-- KEYS[3] 作业hash表的key

local clean_table = {"1","2","3"}

local clean_members = ""

for i, v in ipairs(clean_table) do
    clean_members = clean_members .. " " .. v
end
print(clean_members)
--[[
local clean_timestamp_result = redis.call('ZREM', KEYS[1], clean_members)
local clean_composite_result = redis.call('ZREM', KEYS[2], clean_members)
local clean_list_result redis.call('HDEL', KEYS[3], clean_members)
if clean_timestamp_result and clean_composite_result and clean_list_result then
    return "success"
else
    return "fail"
end
]]--

