var stompClient = null

$(function() {

    connect()
    $('#send').click(function(){
        sendMessage()
    })
})

const connect = function(){

    socket = new SockJS('/our-websocket')
    stompClient = Stomp.over(socket) 
    stompClient.connect({}, function(frame){
        
        stompClient.subscribe('/topic/messages', function(message){
            showMessage(JSON.parse(message.body).mensage)
            
        })
        stompClient.subscribe('user/chat', function(message){
            showMessage(JSON.parse(message.body).mensage)
            
        })
    })
}

const showMessage = function(message) {
    
    $('#messages').append('<tr><td>' + message + '</td></tr>')
    
}

const sendMessage = function(){
    
    stompClient.send('/ws/messages', {}, JSON.stringify({mensage: $('#message').val()}))
    stompClient.send('/ws/chat', {}, JSON.stringify({mensage: $('#message').val()}))
}