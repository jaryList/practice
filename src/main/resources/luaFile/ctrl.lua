require("mobdebug").start()

-- false/nil均为假 true/非nil为真
if true then
    print("true is true")
end

if false then
    print("false is true")
else
    print("false is false")
end

if nil then
    print("nil is true")
else
    print("nil is false")
end

if 0 then
    print("0 is true")
end

-- local b = "string"
-- local b = true
local b = 1
-- local b = nil
-- boolean、nil不可以拼接
if b then
    print(b .. " is true")
else
    print(b .. " is false")
end

-- 多重判断
local c = 10
if c > 100 then
    print("c is more than 100")
elseif c > 20  then
    print("c is more than 20, le 100")
else
    print("c is lt 20")
end