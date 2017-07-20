wifi.setmode(wifi.STATION)
wifi.sta.config("LABROSSONET","xxxxxx")
tmr.create():alarm(1000, 1, function(cb_timer)
  if wifi.sta.getip() == nil then
    print("Connessione...")
  else
    cb_timer:unregister()
    print("Connesso, IP: "..wifi.sta.getip())
    print("telnet in ascolto sulla 2323")
  end
end)
telnet_srv = net.createServer(net.TCP, 180)
telnet_srv:listen(2323, function(socket)
    local fifo = {}
    local fifo_drained = true
    local function sender(c)
        if #fifo > 0 then
            c:send(table.remove(fifo, 1))
        else
            fifo_drained = true
        end
    end
    local function s_output(str)
        table.insert(fifo, str)
        if socket ~= nil and fifo_drained then
            fifo_drained = false
            sender(socket)
        end
    end
    node.output(s_output, 0)
    socket:on("receive", function(c, l)
        node.input(l)
    end)
    socket:on("disconnection", function(c)
        node.output(nil)
    end)
    socket:on("sent", sender)
    print("Benvenuti su Rossonet AR4K micro LUA!")
end)

