local a = 0

if a then
    print("true")
else
    print("false")
end

local cleanMaxRecord = "2"
local cleanTable = {1,3,4,5,6,7}

for i, v in ipairs(cleanTable) do
    print(i.."-"..v)
end
print("------------------")

-- 单次清理条数控制
if #cleanTable > cleanMaxRecord then
    local cleanMaxRecordTable = {}
    for i = 1, cleanMaxRecord do
        table.insert(cleanMaxRecordTable, cleanTable[i])
    end
    cleanTable = cleanMaxRecordTable
end

for i, v in ipairs(cleanTable) do
    print(i.."-"..v)
end

