package com.example.tictactoemvvm.viewModel;

import android.os.Handler;
import android.os.Looper;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tictactoemvvm.R;
import com.example.tictactoemvvm.model.Cell;
import com.example.tictactoemvvm.model.Game;
import com.example.tictactoemvvm.model.Player;

    public class GameViewModel extends ViewModel {

        public ObservableArrayMap<String, Integer> cells;
        private Game game;

        public void init(String p1, String p2) {
            game = new Game(p1, p2);
            cells = new ObservableArrayMap<>();
        }

        public void onClickedAtCell(int r, int c) {
            if (game.cells[r][c] == null) {
                game.cells[r][c] = new Cell(game.currentPlayer);
                int res = 0;
                if (game.currentPlayer.value == Player.PlayerValue.VALUE_X) {
                    res = R.drawable.ic_baseline_cancel_24;
                } else {
                    res = R.drawable.ic_baseline_check_circle_outline_24;
                }
                cells.put(String.valueOf(r) + String.valueOf(c), res);

                if (game.isGameEnded()) {
                    resetGame();
                } else {
                    game.switchPlayer();
                }
            }
        }

        public LiveData<Player> getWinner() {
            return game.winners;
        }

        public void resetGame() {
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    game.reset();
                    cells.clear();
                }
            }, 1000);
        }
    }
