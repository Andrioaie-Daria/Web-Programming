function getUserTopics(userId, callbackFunction){
    $.getJSON("TopicsController", {action: "getUserTopics", userId: userId}, callbackFunction);
}

function getAllTopics(callbackFunction){
    $.getJSON("TopicsController", {action: "getAll"}, callbackFunction);
}

function getCommentsForTopic(topicId, callbackFunction){
    $.getJSON("CommentsController", {action: "getAllForTopic", topicId: topicId}, callbackFunction);
}

function deleteComment(commentId){
    $.ajax( 'CommentsController?commentId=' + commentId, {
        type : 'DELETE'
    });
    //
    // $.ajax( 'CommentsController', {
    //     type : 'DELETE',
    //     data: commentId
    // });

}