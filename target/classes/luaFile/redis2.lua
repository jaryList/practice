if redis.call("EXISTS", KEYS[1]) == 1 then
    return redis.call("INCR", KEYS[1])
else
    return redis.call("SET", KEYS[1], 1)
end