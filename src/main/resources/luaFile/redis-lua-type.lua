local args = ARGV[1] .. " " .. ARGV[2]
local homework_list = redis.call('HMGET', KEYS[1], args)
local type = type(homework_list)
print(type)
print(homework_list)
return homework_list