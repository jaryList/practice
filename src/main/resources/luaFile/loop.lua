-- while循环
local w = 10
while(w < 20) do
    print(w)
    w = w + 1
end


-- for循环
local table = {"a", "b", "c"}
-- 泛型循环
for i, v in ipairs(table) do
    print(i.. "-" .. v)
end
-- 一般循环，三个参数是 开始，截止(初始计算出且只计算一次)，步长(可选)
for i = 1, 10, 2 do
    print("for loop - " .. i)
end

-- repeat until循环
local r = 10
repeat
    print("repeat - " .. r)
    r = r - 1
until r < 0

-- 嵌套循环
local w_f = 3
while(w_f > 0) do
    print("while-for-outer-" .. w_f)
    for i = 1, w_f do
      print("while-for-inner-" .. i)
    end
    w_f = w_f - 1
end

-- 中断循环，break跳出
for i = 1, 10 do
    print("for-break-" .. i)
    if i > 5 then
        print("break")
        break
    end
end