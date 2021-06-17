--[[
运算符优先级
从高到低的顺序：
^
not    - (unary)
*      /       %
+      -
..
<      >      <=     >=     ~=     ==
and
or
--]]

-- 算数运算
local a = 10
local b = 20
print("a + b = " .. a + b)
print("a - b = " .. a - b)
print("a * b = " .. a * b)
print("b / a = " .. b / a)
print("b % a = " .. b % a)
print("a^2 = " .. a^2)



-- 关系运算 & 逻辑运算
if b > a or b == a  then
    print("b > a or b == a")
end

if a < b  and a < 15 then
    print("a < b and a < 15")
end

if not(b < a) then
    print("not(b < a)")
end

-- 逻辑运算优先级，左到右
if false and  false or true then
    print("false and  false or true")
end

if false or  true and false then
    print("false or  true and false")
end

-- 混合

print("混合 = " .. 5 + a^2  * 2 + a ^ 2 / b - b % 2)


-- 其他运算
print("拼" .. "接")
print(#"字符长度") -- 一个中文三个(不常用可能更多),说明是字节长度
print(string.len("字符长度"))
print(#"string.len")
print(string.len("string"))