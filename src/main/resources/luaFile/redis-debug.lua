local value1 = ARGV[1]
local value2 = ARGV[2]
redis.debug(value1)
redis.debug(value2)
if(value1>value2)
then
    return "a"
else
    return "b"
end