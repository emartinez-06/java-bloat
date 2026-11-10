import Link from "next/link";
import axios from "axios";
import { useEffect, useState } from "react";

type VideoGame = {
  id: number;
  name: string;
  platform: string;
  globalSales: number;
};

export default function TopVideoGames() {
  const [games, setGames] = useState<VideoGame[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const controller = new AbortController();

    axios.get<VideoGame[]>("http://localhost:8080/api/top_games?count=10", {
      signal: controller.signal,
    })
      .then((response) => setGames(response.data))
      .catch((error) => {
        if (!axios.isCancel(error)) {
          setError("Could not load video games.");
        }
      })
      .finally(() => {
        if (!controller.signal.aborted) setLoading(false);
      });

    return () => controller.abort();
  }, []);

  return (
    <main>
      <Link href="/">Go to Home</Link>
      <h1>Top Video Games</h1>
      {loading ? (
        <p>Loading video games...</p>
      ) : error ? (
        <p role="alert">{error}</p>
      ) : games.length === 0 ? (
        <p>No video games found</p>
      ) : (
        <ul>
          {games.map((game) => (
            <li key={game.id}>
              <Link href={`/videogame_details/${game.id}`}>{game.name}</Link>
              {" - "}{game.platform}{" - "}Global sales: {game.globalSales}
            </li>
          ))}
        </ul>
      )}
    </main>
  );
}
