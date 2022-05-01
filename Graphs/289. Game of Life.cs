/*
https://leetcode.com/problems/game-of-life/
*/
public class Solution
{
    public void GameOfLife(int[][] board)
    {
        int Nrows = board.Length;
        int Ncolumns = board[0].Length;
        int[][] t_state = new int[board.Length][];
        for (int i = 0; i < t_state.Length; i++)
            t_state[i] = new int[board[0].Length];
        int[] dx = new int[] { -1, 1, 0, 0, -1, 1, -1, 1 };
        int[] dy = new int[] { 0, 0, -1, 1, -1, 1, 1, -1 };
        var Nlive_adj = (int cx, int cy) =>
        {
            int ans = 0;
            for (int i = 0; i < 8; i++)
            {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx >= Ncolumns || nx < 0)
                    continue;
                if (ny >= Nrows || ny < 0)
                    continue;
                if (board[ny][nx] == 1)
                    ans++;
            }
            return ans;
        };
        for (int r = 0; r < board.Length; r++)
        {
            for (int c = 0; c < board[0].Length; c++)
            {
                t_state[r][c] = 0;
                int Nlive = Nlive_adj(c, r);
                if (board[r][c] == 1 && (Nlive == 2 || Nlive == 3))
                    t_state[r][c] = 1;
                else if (board[r][c] == 0 && Nlive == 3)
                    t_state[r][c] = 1;
            }
        }

        for (int r = 0; r < board.Length; r++)
        {
            for (int c = 0; c < board[0].Length; c++)
                board[r][c] = t_state[r][c];
        }
    }
}
