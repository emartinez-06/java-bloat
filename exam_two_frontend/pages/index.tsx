import Link from "next/link";
import axios from "axios";
import { useEffect, useState } from "react";

type GameSummary = {
  id: number;
  name: string;
};

export default function Dashboard() {
  const [games, setGames] = useState<GameSummary[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const controller = new AbortController();

    axios.get<GameSummary[]>("http://localhost:8080/api/videogame/search?name=", {
      signal: controller.signal,
    })
      .then((response) => setGames([...response.data].sort((a, b) => a.name.localeCompare(b.name))))
      .catch((error) => {
        if (!axios.isCancel(error)) setError("Could not load video games.");
      })
      .finally(() => {
        if (!controller.signal.aborted) setLoading(false);
      });

    return () => controller.abort();
  }, []);

  return (
    <main>
      <h1>Dashboard</h1>
      <nav>
        <Link href="/add_videogame">Add Video Game</Link>{" "}
        <Link href="/top_videogames">Top Video Games</Link>
      </nav>
      {loading ? (
        <p>Loading video games...</p>
      ) : error ? (
        <p role="alert">{error}</p>
      ) : games.length === 0 ? (
        <p>No video games in the database</p>
      ) : (
        <ul>
          {games.map((game) => (
            <li key={game.id}>
              <Link href={`/videogame_details/${game.id}`}>{game.name}</Link>
            </li>
          ))}
        </ul>
      )}
    </main>
  );
}
