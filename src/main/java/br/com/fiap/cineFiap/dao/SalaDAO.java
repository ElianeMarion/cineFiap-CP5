package br.com.fiap.cineFiap.dao;

import br.com.fiap.cineFiap.models.Sala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {

    private Connection conexao;

    public List<Sala> listar() {

        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        List<Sala> salas = new ArrayList<>();

        try {
            ps = conexao.prepareStatement("SELECT * FROM TBL_SALA");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Sala sala = new Sala();

                sala.setId(rs.getLong(1));
                sala.setNome(rs.getString(2));
                sala.setPreco(rs.getDouble(3));

                Timestamp timestamp = rs.getTimestamp(4);

                if (timestamp != null) {
                    sala.setDataExclusao(timestamp.toLocalDateTime());
                } else {
                    sala.setDataExclusao(null);
                }

                salas.add(sala);
            }

            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return salas;
    }

    public Sala buscarPorId(Long id) {

        conexao = ConnectionFactory.obterConexao();

        Sala sala = new Sala();

        try {
            PreparedStatement ps = conexao.prepareStatement(
                    "SELECT * FROM TBL_SALA WHERE ID_SALA = ?"
            );

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                sala.setId(rs.getLong(1));
                sala.setNome(rs.getString(2));
                sala.setPreco(rs.getDouble(3));

                Timestamp timestamp = rs.getTimestamp(4);

                if (timestamp != null) {
                    sala.setDataExclusao(timestamp.toLocalDateTime());
                } else {
                    sala.setDataExclusao(null);
                }
            }

            ps.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sala;
    }
}