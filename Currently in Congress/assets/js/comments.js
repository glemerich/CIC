// Define an array to store comments
var comments = [
    { id: 1, user: "", text: "This is a Great Idea!", likes: 0, replies: [] },
    { id: 2, user: "", text: "Interesting Proposal.", likes: 0, replies: [] },
    { id: 3, user: "", text: "Not a fan of this.", likes: 0, replies: [] }
];

var userCounter = 65; // ASCII code for 'A'

// Function to generate fake user names
function generateUserName() {
    var userName = "User " + String.fromCharCode(userCounter);
    userCounter++;
    return userName;
}

// Function to assign user names to existing comments
function assignUserNames() {
    comments.forEach(function(comment) {
        if (comment.user === "") {
            comment.user = generateUserName();
        }
        comment.replies.forEach(function(reply) {
            if (reply.user === "") {
                reply.user = generateUserName();
            }
        });
    });
}

// Function to render comments
function renderComments() {
    var commentsSection = document.getElementById("comments-section");
    commentsSection.innerHTML = "";

    comments.forEach(function(comment) {
        var commentDiv = document.createElement("div");
        commentDiv.classList.add("comment");
        commentDiv.id = "comment-" + comment.id;
        commentDiv.innerHTML = '<p><strong>' + comment.user + '</strong>: ' + comment.text + '</p>' +
            '<button id="like-' + comment.id + '" onclick="likeComment(' + comment.id + ')">Like</button>' +
            '<span id="like-count-' + comment.id + '">' + comment.likes + '</span>' +
            '<button onclick="toggleReplyForm(' + comment.id + ')">Reply</button>' +
            '<div id="reply-form-' + comment.id + '" style="display: none;">' +
                '<form onsubmit="postReply(' + comment.id + '); return false;">' +
                    '<textarea id="reply-text-' + comment.id + '" placeholder="Write your reply here..."></textarea>' +
                    '<button type="submit">Post Reply</button>' +
                '</form>' +
            '</div>' +
            '<div class="replies" id="replies-' + comment.id + '"></div>';
        commentsSection.appendChild(commentDiv);

        // Render replies
        renderReplies(comment.id);
    });
}

// Function to render replies for a comment
function renderReplies(commentId) {
    var repliesDiv = document.getElementById('replies-' + commentId);
    repliesDiv.innerHTML = '';

    var replies = comments.find(function(comment) { return comment.id === commentId; }).replies;
    replies.forEach(function(reply) {
        var replyDiv = document.createElement('div');
        replyDiv.classList.add("comment"); // Add comment class for consistent styling
        replyDiv.innerHTML = '<p><strong>' + reply.user + '</strong>: ' + reply.text + '</p>' +
            '<button onclick="likeReply(' + commentId + ',' + replies.indexOf(reply) + ')">Like</button>' +
            '<span id="like-count-reply-' + commentId + '-' + replies.indexOf(reply) + '">' + reply.likes + '</span>';
        repliesDiv.appendChild(replyDiv);
    });
}

// Function to post a reply to a comment
function postReply(parentCommentId) {
    var replyText = document.getElementById('reply-text-' + parentCommentId).value;
    var parentComment = comments.find(function(c) { return c.id === parentCommentId; });

    if (replyText.trim() !== "") {
        var userName = generateUserName();
        parentComment.replies.push({ user: userName, text: replyText, likes: 0 });
        renderReplies(parentCommentId);

        // Reset the reply form after posting
        document.getElementById('reply-text-' + parentCommentId).value = '';

        // Close the reply form
        toggleReplyForm(parentCommentId);
    }
}

// Function to handle liking a reply
function likeReply(parentCommentId, replyIndex) {
    var parentComment = comments.find(function(c) { return c.id === parentCommentId; });
    if (parentComment && parentComment.replies.length > replyIndex) {
        parentComment.replies[replyIndex].likes++;
        renderReplies(parentCommentId);
    }
}

// Function to toggle reply form visibility
function toggleReplyForm(commentId) {
    var replyForm = document.getElementById('reply-form-' + commentId);
    if (replyForm.style.display === 'none') {
        replyForm.style.display = 'block';
    } else {
        replyForm.style.display = 'none';
    }
}

// Function to handle liking a comment
function likeComment(commentId) {
    var comment = comments.find(function(c) { return c.id === commentId; });
    if (comment) {
        comment.likes++;
        renderComments();
    }
}

// Function to handle posting a new comment
function postComment() {
    var commentText = document.getElementById("comment-text").value;
    if (commentText.trim() !== "") {
        var userName = generateUserName();
        var newComment = {
            id: comments.length + 1,
            user: userName,
            text: commentText,
            likes: 0,
            replies: []
        };
        comments.push(newComment);
        renderComments();
        document.getElementById("comment-text").value = "";
    }
}

// Assign user names to existing comments
assignUserNames();

// Render initial comments
renderComments();