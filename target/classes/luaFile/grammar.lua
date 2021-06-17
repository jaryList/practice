-- 单行注释

--[[
多行注释
多行注释
--]]

-- 标识符
--[[
大小写英文字母开头 + 数字/下划线
最好不要使用大写 + 下划线，lua有保留字

--]]
m = 1
m1 = 2
m_g = 3
M = 4

-- 全局变量
global_param = 1

print("global_param = " .. global_param)

-- 本地变量
local local_param = 1

-- 全局变量删除，设置成空即可， 空即nil
global_param = nil
print(global_param)
-- 下面会抛异常，nil不可以再进行拼接操作
-- print("set nil global_param = " .. global_param)

