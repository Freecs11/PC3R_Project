import { Avatar, Box, Card, CardContent, Divider } from "@mui/material";

export function CardProfile({ image, health, price, name, createdAt, type, purchasedAt }) {
    return (
        <Card
            sx={{
                borderRadius: "12px",
                minWidth: 128,
                textAlign: "center",
                boxShadow:
                    "0 2px 4px -2px rgba(0,0,0,0.24), 0 4px 24px -2px rgba(0, 0, 0, 0.2)",
            }}
        >
            <CardContent>
                <Avatar
                    src={image}
                    sx={{
                        width: 60,
                        height: 60,
                        margin: "auto",
                    }}
                />
                <Box
                    component="h3"
                    sx={{
                        fontSize: 12,
                        fontWeight: "bold",
                        letterSpacing: "0.5px",
                        marginTop: 1,
                        marginBottom: 0,
                    }}
                >
                    {name}
                </Box>
                <Box
                    component="span"
                    sx={{
                        fontSize: 10,
                        color: "grey.500",
                        marginBottom: "0.875em",
                    }}
                >
                    {type}
                </Box>
            </CardContent>
            <Divider light />
            <Box display={"flex"}>
                <Box
                    p={2}
                    flex={"auto"}
                    sx={{
                        position: "relative",
                        "&:not(:last-of-type)": {
                            "&:after": {
                                content: '" "',
                                display: "block",
                                position: "absolute",
                                height: "50%",
                                width: "1px",
                                backgroundColor: "rgba(0 0 0 / 0.08)",
                                top: "50%",
                                right: 0,
                                transform: "translateY(-50%)",
                            },
                        },
                    }}
                >
                    <Box
                        sx={{
                            fontSize: 12,
                            color: "grey.500",
                            fontWeight: 500,
                            fontFamily:
                                '-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol"',
                            margin: 0,
                        }}
                    >
                        Health
                    </Box>
                    <Box
                        component="p"
                        sx={{
                            fontSize: 20,
                            fontWeight: "bold",
                            marginBottom: 0.5,
                            letterSpacing: "1px",
                        }}
                    >
                        {health}
                    </Box>
                </Box>
                <Box
                    p={2}
                    flex={"auto"}
                    sx={{
                        position: "relative",
                        "&:not(:last-of-type)": {
                            "&:after": {
                                content: '" "',
                                display: "block",
                                position: "absolute",
                                height: "50%",
                                width: "1px",
                                backgroundColor: "rgba(0 0 0 / 0.08)",
                                top: "50%",
                                right: 0,
                                transform: "translateY(-50%)",
                            },
                        },
                    }}
                >
                    <Box
                        sx={{
                            fontSize: 12,
                            color: "grey.500",
                            fontWeight: 500,
                            fontFamily:
                                '-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol"',
                            margin: 0,
                        }}
                    >
                        Price
                    </Box>
                    <Box
                        component="p"
                        sx={{
                            fontSize: 20,
                            fontWeight: "bold",
                            marginBottom: 0.5,
                            letterSpacing: "1px",
                        }}
                    >
                        {price}
                    </Box>
                </Box>
            </Box>
        </Card>
    );
}