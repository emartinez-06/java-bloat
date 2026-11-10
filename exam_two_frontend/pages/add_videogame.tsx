import Link from "next/link";

export default function AddVideoGame() {
  return (
    <main>
      <Link href="/">Go to Home</Link>
      <h1>Add Video Game</h1>
      <form>
        <label>Rank <input name="rank" type="number" min="1" required /></label>
        <label>Name <input name="name" type="text" required /></label>
        <label>Platform <input name="platform" type="text" required /></label>
        <label>Release Year <input name="releaseYear" type="number" min="1950" max="2026" required /></label>
        <label>Genre <input name="genre" type="text" required /></label>
        <label>Publisher <input name="publisher" type="text" required /></label>
        <label>NA Sales <input name="naSales" type="number" min="0" step="any" required /></label>
        <label>EU Sales <input name="euSales" type="number" min="0" step="any" required /></label>
        <label>JP Sales <input name="jpSales" type="number" min="0" step="any" required /></label>
        <label>Other Sales <input name="otherSales" type="number" min="0" step="any" required /></label>
        <button type="submit" disabled>Submit</button>
      </form>
    </main>
  );
}
