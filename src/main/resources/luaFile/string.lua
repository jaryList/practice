local str = "Hello Lua list"
print(string.lower(str))
print(string.upper(str))
print(string.gsub(str,"l", "k"))
print(string.find(str, "list"))
print(string.reverse(str))
print(string.format("this value is %d %s", 4, "kk"))
print(string.len(str))
print(string.rep("abc",2))
print(string.sub("abcdefg", 2, 3))

for word in string.gmatch("Hello Lua user", "%a+") do
    print(word)
end