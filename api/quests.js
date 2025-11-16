// api/quests.js

let quests = [
  {
    id: 1,
    title: "Math Basics",
    description: "Solve basic arithmetic problems",
    xp: 100,
  },
  {
    id: 2,
    title: "History Quiz",
    description: "Answer 10 questions on history",
    xp: 150,
  },
  {
    id: 3,
    title: "Science Challenge",
    description: "Complete a science trivia",
    xp: 200,
  },
];

module.exports = (req, res) => {
  const method = req.method;
  const query = req.query || {};

  if (method === "GET") {
    const { title } = query;

    if (title) {
      const lower = String(title).toLowerCase();
      const result = quests.filter(
        (q) => q.title.toLowerCase() === lower
      );
      return res.status(200).json(result);
    }

    return res.status(200).json(quests);
  }

  if (method === "POST") {
    const body = req.body || {};
    const { title, description, xp } = body;

    if (!title || !description || typeof xp === "undefined") {
      return res
        .status(400)
        .json({ message: "title, description and xp are required" });
    }

    const newId =
      quests.length === 0
        ? 1
        : quests[quests.length - 1].id + 1;

    const newQuest = {
      id: newId,
      title: String(title),
      description: String(description),
      xp: Number(xp),
    };

    quests.push(newQuest);
    return res.status(201).json(newQuest);
  }

  res.setHeader("Allow", ["GET", "POST"]);
  return res
    .status(405)
    .json({ message: `Method ${method} Not Allowed` });
};
