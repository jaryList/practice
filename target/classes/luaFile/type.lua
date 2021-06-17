-- 数据类型
print(type(1))
print(type("string"))
print(type(print))
print(type(type))
print(type(true))
print(type(false))
print(type(nil))
print(type(a))



-- number类型
print(type(1))
print(type(1.0))
print(type(2e+1))
print(3e+2)
print(2e+1)




-- 运算时，会尝试数字类型转换
print(2 + 6)
print("2" + 6)
print("2" + "6")




-- 多行字符串
local linesString = [[
   line1
   line2
   line3
]]
print(linesString)





-- table表 键值对
-- 创建一个空的 table
local tbl1 = {}

-- 直接初始表
local tbl2 = {"apple", "pear", "orange", "grape"}

for i, v in ipairs(tbl2) do
    print(i.. "-" ..v)
end


tab1 = { key1 = "val1", key2 = "val2", "val3", "val4"}
for k, v in pairs(tab1) do
    print(k .. " - " .. v)
end

tab1.key1 = nil
for k, v in pairs(tab1) do
    print(k .. " - " .. v)
end





-- 同表可以不同类型key
local a = {}
a["key"] = 1
key = 10
a[10] = 10
print(a["key"])
print(a[key])


-- 长度计算使用#, 统计数组长度，k-v不算长度
print(#"stringlength")
local table_length1  = {"l1"}
local table_length2  = {"l1","l2"}
local table_length3  = {"l1","l2", k1 = "1"}
local table_length4  = {k1 = "1"}
print("table_length = "..#table_length4)



-- function
-- 函数定义
function test()
    print("function test")
end
-- 函数需要指定调用
test()

-- 函数可以赋值给变量
function toPara()
    print("函数赋值给变量")
end
-- 赋值时只用函数名
local param_f = toPara
-- 调用时还是函数方式调用
param_f()

-- 匿名函数调用
function noFun(tab, fun)
    for k, v in pairs(tab) do
        print(fun(k,v))
    end
end

tab = {"t1","t2"}
noFun(tab,function(k,v)
    return k.. "-" .. v
end)

print(table.unpack(tab))

