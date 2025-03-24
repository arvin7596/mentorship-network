use("mentorship_network");

/* CREATE COLLECTION */
db.createCollection("posts_feedback");

/* INSERT POST FEEDBACK */
db.posts_feedback.insertOne({
  _id: ObjectId(),
  post_id: 1,
  user_id: 3,
  is_liked: true,
  timestamp: new Date(),
  comment: [{ date: new Date(), text: "This is a comment" }],
  is_shared: true,
  is_saved: true,
});

/* INSERT MULTIPLE POST FEEDBACK */
db.posts_feedback.insertMany([
  {
    _id: ObjectId(),
    post_id: 1,
    user_id: 2,
    is_liked: true,
    timestamp: new Date(),
    comment: [{ date: new Date(), text: "This is a comment 1" }],
    is_shared: true,
    is_saved: true,
  },
  {
    _id: ObjectId(),
    post_id: 1,
    user_id: 1,
    timestamp: new Date(),
    comment: [{ date: new Date(), text: "This is a comment 2" }],
  },
  {
    _id: ObjectId(),
    post_id: 2,
    user_id: 3,
    timestamp: new Date(),
  },
  {
    _id: ObjectId(),
    post_id: 2,
    user_id: 1,
    is_liked: true,
    timestamp: new Date(),
    comment: [{ date: new Date(), text: "Test Comment 4" }],
  },
  {
    _id: ObjectId(),
    post_id: 3,
    user_id: 2,
    timestamp: new Date(),
  },
  {
    _id: ObjectId(),
    post_id: 3,
    user_id: 3,
    timestamp: new Date(),
    is_shared: true,
    is_liked: true,
  },
  {
    _id: ObjectId(),
    post_id: 4,
    user_id: 1,
    timestamp: new Date(),
    is_shared: true,
    comment: [{ date: new Date(), text: "Test Comment 7" }],
  },
]);

/* ####################  FIND SPECIFIC POST FEEDBACK  #################### */

// Find all posts feedback
db.posts_feedback.find();

// Find all posts feedback with comments
db.posts_feedback.find({ comment: { $exists: true } });

// Find all posts feedback without comments
db.posts_feedback.find({ comment: { $exists: false } });

// Find all posts feedback with is_liked
db.posts_feedback.find({ is_liked: { $exists: true } });

// Find all posts feedback with comments and only return _id and comment
db.posts_feedback.find({}, { _id: 1, comment: 1 });

// Find all posts feedback with comments containing "This is a comment 1"
db.posts_feedback.find({
  comment: { $elemMatch: { text: "This is a comment 1" } },
});

/* UPDATE POST FEEDBACK */

// Update is_liked to false for post feedback with _id 507f1f77bcf86cd799439011
db.posts_feedback.updateOne(
  { _id: ObjectId("67e17dc79ae494ae5933ad05") },
  { $set: { is_liked: false } }
);

// Update is_liked to false for all posts feedback with is_liked true
db.posts_feedback.updateMany({ is_liked: true }, { $set: { is_liked: false } });

/* ####################  DELETE POST FEEDBACK  #################### */

// Delete post feedback with _id 507f1f77bcf86cd799439011
db.posts_feedback.deleteOne({ _id: ObjectId("67e17dc79ae494ae5933ad05") });

// Delete all posts feedback with is_shared true
db.posts_feedback.deleteMany({
  is_shared: true,
});

/* ####################  AGGREGATE  #################### */

// Aggregate posts feedback by user_id
db.posts_feedback.aggregate([
  {
    $match: {
      user_id: 1,
    },
  },
]);

// Find all users
db.posts_feedback.aggregate([
  {
    $group: {
      _id: "$user_id",
    },
  },
]);

// Find the number of likes for post_id 1
db.posts_feedback.aggregate([
  {
    $match: {
      is_liked: true,
      post_id: 1,
    },
  },
  {
    $count: "Total likes",
  },
]);

// Get Top 3 Most liked posts
db.posts_feedback.aggregate([
  {
    $match: {
      is_liked: true,
    },
  },
  {
    $group: {
      _id: "$post_id",
      likeCount: { $sum: 1 },
    },
  },
  {
    $sort: {
      likeCount: -1,
    },
  },
  {
    $limit: 3,
  },
]);

// Find the most active user based on the number feedback they have made
db.posts_feedback.aggregate([
  {
    $group: {
      _id: "$user_id",
      postCount: { $sum: 1 },
    },
  },
  {
    $sort: {
      postCount: -1,
    },
  },
  {
    $limit: 1,
  },
]);

// Create index for user_id and post_id
db.posts_feedback.createIndex({ user_id: 1, post_id: 1 });

db.posts_feedback
  .find({ user_id: ObjectId("507f1f77bcf86cd799439011") })
  .explain("executionStats");

db.posts_feedback.getIndexes();
