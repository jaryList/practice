--[[
optional_function_scope function function_name( argument1, argument2, argument3..., argumentn)
    function_body
    return result_params_comma_separated
end
--]]

--[[
optional_function_scope: 该参数是可选的制定函数是全局函数还是局部函数，未设置该参数默认为全局函数，如果你需要设置函数为局部函数需要使用关键字 local。

function_name: 指定函数名称。

argument1, argument2, argument3..., argumentn: 函数参数，多个参数以逗号隔开，函数也可以不带参数。

function_body: 函数体，函数中需要执行的代码语句块。

result_params_comma_separated: 函数返回值，Lua语言函数可以返回多个值，每个值以逗号隔开。
--]]


-- 无入参、无返回值函数
function f()
    print("f function")
end

-- 有入参、无返回值函数
function f2(k)
    print("f2 function with arg " .. k)
end

-- 多个参、无返回值函数
function f3(k1, k2, k3)
    print("f2 function with args " .. k1 .. k2 .. k3)
end

-- 无入参、有返回值函数
function f4()
    print("return 6")
    return 6
end

-- 有入参、返回多值函数
function f5(k)
    return 1, 2 * k
end

-- 函数赋值参数，不可以有函数名，即为匿名函数，或者说变量为其函数名
local p_f = function()
   print("将函数赋值给变量")
end

-- 函数需要调用
p_f()
-- 入参没有强校验匹配，主要以函数名为主
f4(1)

-- 可变参数函数 ...表示可变入参
function f6(...)
    --  select("#",...) 来获取可变参数的数量
    print("入参个数是" .. select("#",...))
    -- ipairs使用大括号，非小括号循环
    for i, v in ipairs{...} do
        print(i .. " -可变参数数组-" .. v)
    end
end
--[[
select('#', …) 返回可变参数的长度。
select(n, …) 用于返回参数列表中的第 n 个参数。
--]]
f6(1,2,3,4)

-- 固定参数必须放在变长参数之前
function f7(arg1,...)

end